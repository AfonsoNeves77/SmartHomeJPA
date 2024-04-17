package smarthome.domain.actuator.externalservice;

public interface ActuatorExternalService {
    public boolean executeCommandSim();
    public boolean executeIntegerCommandSim(int position);
    boolean executeDecimalCommand(double value);
}
