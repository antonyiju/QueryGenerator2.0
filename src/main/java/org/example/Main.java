package org.example;

import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
// always need to join, rename the tables, and also the columns to be shown
//SELECT * FROM pg2_sf0001_supplier INNER JOIN pg1_sf0001_customer ON pg2_sf0001_supplier.nationkey = pg1_sf0001_customer.nationkey;
// Need the table from berkai
// join column filter column and tables will be given
//SELECT count(r.r_regionkey) AS cnt FROM pg3_sf0001_region as r, pg2_sf0001_nation as n WHERE r.r_regionkey = n.n_regionkey and r.r_regionkey >= 0
public class Main {
    public static void main(String[] args) {
        //Given by user , these are just examples for test purpose
        String table1 = "pg2_sf0001_supplier"; //a
        String join_col1 = "s_nationkey";
        String table2 = "pg1_sf0001_customer" ; //b
        String join_col2 = "c_nationkey";//will the prefix be given?
        String fil_col  = "s_accntbal";
        Table t1 = new Table(table1, join_col1, "a");
        Table t2 = new Table(table2, join_col2, "b");
        //check type
        //which table does fil_col belong to a or b
        // = or <=
        //

        Column fil_obj = new Column(fil_col, new Table[]{t1,t2});
        //right now code only accepts numerical filters with unique values greater than 100
        String q = "SELECT " +t1.id+"."+t1.col + " as c FROM " + t1.name + " AS " +t1.id+", " + t2.name + " AS " + t2.id + " WHERE "+t1.id+"."+join_col1 +  " = "+t2.id+"."+join_col2 + " AND ";
        List<String> l = fil_obj.addFilter(q);
        for (String el: l){
            System.out.println(el);
        }
        //Get , min, max, maxrows(t1,t2), number of unique entries in fil_col from berkai

    }
    //SELECT count(r.r_regionkey) AS cnt FROM pg3_sf0001_region as r, pg2_sf0001_nation as n WHERE r.r_regionkey = n.n_regionkey and r.r_regionkey >= 0
}