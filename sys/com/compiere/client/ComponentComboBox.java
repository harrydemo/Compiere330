package com.compiere.client;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ComponentComboBox extends JComboBox
  implements TableCellEditor, TableCellRenderer
{
  private static final long serialVersionUID = 1L;

  public ComponentComboBox(String[] paramArrayOfString)
  {
    super(paramArrayOfString);
    setBorder(null);
  }

  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    Object localObject = paramJTable.getValueAt(paramInt1, paramInt2);
    if ((SysEnv.m != 0) || ((localObject instanceof ComponentComboBox)))
    {
      ComponentComboBox localComponentComboBox = (ComponentComboBox)localObject;
      localComponentComboBox.setSelectedItem(paramObject);
      return localComponentComboBox;
    }
    return null;
  }

  public Component getTableCellEditorComponent(JTable paramJTable, Object paramObject, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    Object localObject = paramJTable.getValueAt(paramInt1, paramInt2);
    if ((SysEnv.m != 0) || ((localObject instanceof ComponentComboBox)))
    {
      ComponentComboBox localComponentComboBox = (ComponentComboBox)localObject;
      localComponentComboBox.setSelectedItem(paramObject);
      return localComponentComboBox;
    }
    return null;
  }

  public void addCellEditorListener(CellEditorListener paramCellEditorListener)
  {
  }

  public void cancelCellEditing()
  {
  }

  public Object getCellEditorValue()
  {
    return null;
  }

  public boolean isCellEditable(EventObject paramEventObject)
  {
    return true;
  }

  public void removeCellEditorListener(CellEditorListener paramCellEditorListener)
  {
  }

  public boolean shouldSelectCell(EventObject paramEventObject)
  {
    int i = SysEnv.m;
    if (i == 0)
      if ((paramEventObject instanceof MouseEvent))
      {
        MouseEvent localMouseEvent = (MouseEvent)paramEventObject;
        if (i == 0);
        return localMouseEvent.getID() != 506;
      }
    return true;
  }

  public boolean stopCellEditing()
  {
    return true;
  }
}