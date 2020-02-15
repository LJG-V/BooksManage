package gui;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class SimpleModel<T> extends AbstractTableModel {
    protected List<String> columnNames;
    protected List<T> rows;

    public SimpleModel(List<String> columnNames,List<T> rows){
        this.columnNames =  columnNames;
        this.rows = rows;
    }

    public List<String> getColumnNames(){ return columnNames;}

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        try {
            List<Method> getMethods = ClassRefect.getAllGetMethod(rows.get(rowIndex));
            return getMethods.get(columnIndex).invoke(rows.get(rowIndex), null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return "";
    }
}
