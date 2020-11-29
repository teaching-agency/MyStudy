package jframe;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhenghaiyang
 * Date: 2020/11/1
 * Description:
 */
public class DepartMentDto{
    private int id;
    private String name;

    public DepartMentDto(){
    }

    public DepartMentDto(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "DepartMentDto{"+
                "id="+id+
                ", name='"+name+'\''+
                '}';
    }
}
