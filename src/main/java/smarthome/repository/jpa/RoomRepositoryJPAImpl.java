package smarthome.repository.jpa;

import jakarta.persistence.*;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomDataModel;
import smarthome.domain.room.RoomFactory;
import smarthome.repository.RoomRepository;
import smarthome.vo.roomvo.RoomIDVO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryJPAImpl implements RoomRepository {

    private final RoomFactory roomFactory;

    private final String PERSISTENCE_UNIT_NAME = "smartHomeJPA";

    public RoomRepositoryJPAImpl(RoomFactory roomFactory) {
        this.roomFactory = roomFactory;
    }

    //Talk about try catch with resources and auto closable interface

    private EntityManager getEntityManager(){

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            return factory.createEntityManager();
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public boolean save(Room room) {
        if(room == null){
            throw new IllegalArgumentException("Entity is null");
        }

        RoomDataModel roomDataModel = new RoomDataModel(room);

        EntityManager em = getEntityManager();

        if(em != null) {
            try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(roomDataModel);
                tx.commit();
                return true;
            } catch (RuntimeException e) {
                // Rollback the transaction if an exception occurs
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();

                }
                return false;

            } finally {
                em.close();
            }
        }
        return false;
    }


    @Override
    public Iterable<Room> findAll() {
        EntityManager em = getEntityManager();

        try (em) {
            if (em == null) {
                return Collections.emptyList();
            }
            Query query = em.createQuery("SELECT r FROM RoomDataModel r");
            List<RoomDataModel> rooms = query.getResultList();
            return RoomDataModel.toDomain(roomFactory, rooms);
        } catch (RuntimeException e) {
            //AQUI DEVE LANÇAR UMA RUNTIME!!!
            return Collections.emptyList();
        }

    }

    private Optional<RoomDataModel> getDataModelFromId(RoomIDVO roomIDVO){
        EntityManager em = getEntityManager();

        try (em) {
            if (em == null) {
                return Optional.empty();
            }

            String idString = roomIDVO.getID();
            return Optional.ofNullable(em.find(RoomDataModel.class, idString));
        }
    }

    @Override
    public Room findById(RoomIDVO id) {
        Optional<RoomDataModel> roomDataModel = getDataModelFromId(id);
        return roomDataModel.map(dataModel -> RoomDataModel.toDomain(roomFactory, dataModel)).orElse(null);
    }


    @Override
    public boolean isPresent(RoomIDVO id) {
        Optional<Room> roomDataModel = Optional.ofNullable(findById(id));
        return roomDataModel.isPresent();
    }


    @Override
    public boolean updateRoom(Room room) {
        RoomIDVO roomIDVO = room.getId();
        Optional<RoomDataModel> roomDataModel = getDataModelFromId(roomIDVO);

        if(roomDataModel.isPresent()){
            RoomDataModel dataModel = roomDataModel.get();
            dataModel.updateFromDomain(room);

            EntityManager em = getEntityManager();

            if(em != null) {
                try {
                    EntityTransaction tx = em.getTransaction();
                    tx.begin();
                    em.merge(dataModel);
                    tx.commit();
                    return true;
                } catch (RuntimeException e) {
                    // Rollback the transaction if an exception occurs
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();

                    }
                    return false;

                } finally {
                    em.close();
                }
            }
            return false;
        }
        return false;
    }
}
