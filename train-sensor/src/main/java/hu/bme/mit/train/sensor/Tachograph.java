package hu.bme.mit.train.sensor;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Created by meres on 2/19/18.
 */
public class Tachograph {
    public enum TachoColumns {
        CurrentTime,
        JoystickPosition,
        ReferenceSpeed
    }
    private Table<Integer, TachoColumns, Long> values = HashBasedTable.create();
    private int currentRow=0;

    public void logValue(int joystickPosition, int referenceSpeed){
        values.put(currentRow,TachoColumns.CurrentTime, System.currentTimeMillis());
        values.put(currentRow,TachoColumns.JoystickPosition, (long)joystickPosition);
        values.put(currentRow,TachoColumns.ReferenceSpeed, (long)referenceSpeed);
        currentRow++;

    }

    public Table getValues(){
        return values;
    }

}
