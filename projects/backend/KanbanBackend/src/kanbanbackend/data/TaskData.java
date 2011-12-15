/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.data;

/**
 *
 * @author Ian h
 */
public class TaskData {
    public String description;
    public int columnIndex;
    public String postitFont;
    public String positStyle;
    public String postitWeight;
    public int postitSize;
    public int positColor;
    public int stageId;
    public int userId;

    public TaskData(){}

    public TaskData(String description, int columnIndex, String postitFont, String positStyle, String postitWeight, int postitSize, int positColor, int stageId, int userId) {
        this.description = description;
        this.columnIndex = columnIndex;
        this.postitFont = postitFont;
        this.positStyle = positStyle;
        this.postitWeight = postitWeight;
        this.postitSize = postitSize;
        this.positColor = positColor;
        this.stageId = stageId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "[description:"+description+"]" +
                "[columnIndex:" + columnIndex + "]" +
                "[stageId:" + stageId + "]" +
                "[userId:" + userId + "]";
    }



}
