package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity 
public class Bond extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public String name;
    
    public static Finder<Long,Bond> find = new Finder<Long,Bond>(Long.class, Bond.class); 
    
    public static Page<Bond> page(int page, int pageSize, 
    								  String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("company")
                .findPagingList(pageSize)
                .getPage(page);
    }
    
}

