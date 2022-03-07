package utils;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class ObjectTableModel<T> extends AbstractTableModel {
    private List<T> objectRows=new ArrayList<>();
    @Override
    public int getRowCount() {
        if(objectRows!=null) {
            return objectRows.size();
        }
        return -1;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T t=objectRows.get(rowIndex);
        return  getValueAt(t,columnIndex);
    }

    public abstract Object getValueAt(T t, int columnIndex);

    @Override
    public abstract String getColumnName(int column);

    public abstract String getFieldName(int column);

    public List<T> getObjectRows() {
        return objectRows;
    }


    public void setObjectRows(List<T> objectRows) {
        this.objectRows = objectRows;
    }

    public T getObjectFromSelectedRow(int row) {
        if(row>=0) {
            return objectRows.get(row);
        }
        return null;
    }

    public void deleteObjectFromSelectRow(int row){
        if(objectRows!=null) {
            this.objectRows.remove(row);
        }
    }

}
