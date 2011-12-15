/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.data;


/**
 *
 * @author Ian
 */
public class ProjectData {
    public String userEmail, name,company, manager, developer, startDate, endDate;
    public String postitFont, postitStyle, postitWeight;
    public int postitSize, postitColor, numColumns;
    public String colName1, colName2, colName3, colName4, colName5, colName6, colName7;
    public int id;
    public String[] users;

    @Override
    public String toString() {
        return  "[name:" + name + "]" +
                "[company:" + company + "]" +
                "[manager:" + manager + "]" +
                "[developer:" + developer + "]" +
                "[columns:" + numColumns + "]" +
                "[users:" + users + "]";
    }

    
}
