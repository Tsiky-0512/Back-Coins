/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDea.backend.database;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Access {

    
    public static int[][]intersection(ResultSetMetaData info,Method[]m)throws Exception{
            ArrayList indiceInClass=new ArrayList(),
            indiceInTable=new ArrayList();
            for(int i=0;i<m.length;i++)
            {
                    for(int j=1;j<=info.getColumnCount();j++)
                    {
                    String nameInClass=m[i].getName().toLowerCase();
                    String nameInTable="set"+info.getColumnName(j).toLowerCase();
                            if (nameInClass.compareTo(nameInTable)==0)
                            {
                                    indiceInClass.add(i);
                                    indiceInTable.add(j);

                            }

                    }
            }
            int rep[][]=new int[2][indiceInClass.size()];
            for (int i=0;i<indiceInClass.size();i++)
            {
                    rep[0][i]=(int)indiceInClass.get(i);
                    rep[1][i]=(int)indiceInTable.get(i);
            }
            return rep;
    }

    public static <T> T[] query(Connection c,Class<T> cls,String requette)throws Exception{


    ArrayList <T> rep;
    T[] xx;
    
        PreparedStatement ps=c.prepareStatement(requette); //miexecuter requette
        ResultSet r=ps.executeQuery();
        ResultSetMetaData info=r.getMetaData(); //maka info:nom_colonne ..isana colonne sns...
        Method[]m=cls.getMethods();//maka method anatsofoana donnée
        int [][]inter=intersection(info,m);//intersection table/class ->ze Attribut mis ao amn class sady mis ao amn base ihany no apesaina
        rep = new ArrayList<>(); // anatsofoana anle ligne...vectore satri tsy azo ni nombre de ligne
        while(r.next())//reef maka donnée iz de ligne par ligne -> r.next() mifindra ligne
        {
            Object objetAsinaDonne =cls.newInstance(); //mamorona objec asina donnée oatra oe "new Personne()"
            for (int i=0;i<inter[0].length;i++)
            {
                int k=inter[0][i],l=inter[1][i];
                setValue(objetAsinaDonne,m[k],r.getObject(l));
            }
            rep.add((T)objetAsinaDonne);
        }   xx = (T[]) Array.newInstance(cls,rep.size());


            return rep.toArray(xx);
    }
    public static void setValue(Object rep,Method m,Object value)throws Exception{
            if(value!=null)
            {
                    Class cls=m.getParameterTypes()[0];
                    /*System.out.println("tyyyyyyy"+cls.getSimpleName());
                    if(cls.getSimpleName().compareTo("int")==0)
                            value=((java.math.BigDecimal)value).intValue();
                    else if(cls.getSimpleName().compareTo("float")==0)
                            value=((java.math.BigDecimal)value).floatValue();
                    */
                    if(cls.getSimpleName().compareTo("long")==0)
                            value=new BigDecimal(""+value).longValue();
                    m.invoke(rep,value);
            }
    }
    public static Object [][] find(Connection c,String requette) throws Exception{
    Object[][] xx;
   
        PreparedStatement ps=c.prepareStatement(requette); //miexecuter requette
        ResultSet r=ps.executeQuery();
        ArrayList rep=new ArrayList(); // anatsofoana anle ligne...ArrayList satri tsy azo ni nombre de ligne
        ResultSetMetaData info=r.getMetaData();
        while(r.next())//reef maka donnée iz de ligne par ligne -> r.next() mifindra ligne
        {
            Object[] ligne=new Object[info.getColumnCount()];
            for (int i=0;i<info.getColumnCount();i++)
                ligne[i]=r.getObject(i+1);
            rep.add(ligne);
        }   xx = new Object[rep.size()] [info.getColumnCount()];
        for(int i=0;i<rep.size();i++)
        {
            Object[] k=(Object[])rep.get(i);
            for(int j=0;j<info.getColumnCount();j++)
                xx[i]=k;

        }
   
            return xx;
    }
    public static void execute(Connection c,String sql) throws Exception{
        
        PreparedStatement ps=c.prepareStatement(sql); //miexecuter requette
        ps.execute();
    } 
    public static void executeBash(Connection c,String sql) throws Exception{
        
        Statement  ps=c.createStatement(); //miexecuter requette
        ps.addBatch(sql);
        ps.executeBatch();
    } 
}
