package smarthome.domain.actuator.externalservice;

/**
 * SimHardwareAct serves as an abstraction representing an external hardware entity that interacts with the system.
 * It encapsulates the functionality and behavior of the hardware component that the associated objects act upon.
 */


public class SimHardwareAct implements ActuatorExternalService{

    /**
     * This method is used to execute a command on the simulated hardware.
     * @return True if the command was executed successfully, false otherwise.
     */
    public boolean executeCommandSim() {
        return true;
    }

    public boolean executeIntegerCommandSim(int position) {
        return true;
    }

    /**
     * This method is used to execute a command on the simulated hardware.
     * @param value The value to be set
     * @return True if the command was executed successfully, false otherwise (at the moment, it is defined to be
     * always true, since hardware interaction is still unknown).
     */
    public boolean executeDecimalCommand(double value){
        return true;
    }

}
