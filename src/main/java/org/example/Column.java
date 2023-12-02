package org.example;

import java.util.ArrayList;
import java.util.List;

public class Column {
    String name;
    String type;
    Table f_table;
    Table table;
    int size;
    //give berkai join columns
    public Column(String name, Table[] tables){
        this.name = name;
        this.table = tables[0];// berkai getTable(tables,);
        for (Table i : tables){
            if (!(i.name.equals(table.name))) f_table = i;
        }
        this.size = 10000;//get count of unique values getCount(name)
        this.table.size = 10000; // get count of table row getTableSize(table)
        this.type = "number" ;// berkai getType();
    }

    public String getType() {

        return type;
    }

    // filter
    public List<String> addFilter(String q){
        List<String> results = new ArrayList<>();
        switch (type){
            case "number" -> {
                //need the following from berkai if size is ignore tables
                if (size < 10){
                    return null;
                }
                else {
                    float min = 10000.56f;//getMin(this);
                    float max = 99999.526f;//getMax(this);
                    float step = (max-min)/(size/10);
                    for (float i = min; i <= max; i = i+step){
                        results.add(q + table.id + "." + name + " >= " + i);
                    }

                }
            }
            case "string" -> {}
            case "date" -> {}
            default -> {}
        }
        return results;
    }
}
