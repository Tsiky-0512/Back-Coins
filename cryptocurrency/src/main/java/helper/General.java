package helper;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class General {
	public int[][]intersection(ResultSetMetaData info,Method[]m)throws Exception{
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
	
	public void setValue(Object rep,Method m,Object value)throws Exception{
        if(value!=null)
        {
                Class cls=m.getParameterTypes()[0];
                if(cls.getSimpleName().compareTo("double")==0)
                        value=new BigDecimal(""+value).doubleValue();
                m.invoke(rep,value);
        }
	}

	public <T> T[] find(Connection c,Class<T> cls,String requette)throws Exception{
		ArrayList <T> rep;
		T[] xx;
	    PreparedStatement ps=c.prepareStatement(requette);
	    ResultSet r=ps.executeQuery();
	    ResultSetMetaData info=r.getMetaData();
	    Method[]m=cls.getMethods();
	    int [][]inter=intersection(info,m);
	    rep = new ArrayList<>();
	    while(r.next())
	    {
	        Object objetAsinaDonne =cls.newInstance();
	        for (int i=0;i<inter[0].length;i++)
	        {
	            int k=inter[0][i],l=inter[1][i];
	            setValue(objetAsinaDonne,m[k],r.getObject(l));
	        }
	        rep.add((T)objetAsinaDonne);
	    }   xx = (T[]) Array.newInstance(cls,rep.size());
	        return rep.toArray(xx);
	}
	
	public void insertOrDelete(Connection c ,String req){
	    Statement stmt = null;
	    try {
	        c.setAutoCommit(false);
	        stmt = c.createStatement();
	        stmt.executeUpdate(req);
	        stmt.close();
	        c.commit();
	        c.close();
	    } catch (Exception e) {
	    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	    }
	}
}
