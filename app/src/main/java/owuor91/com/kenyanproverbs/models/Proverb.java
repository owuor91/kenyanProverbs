
package owuor91.com.kenyanproverbs.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Table;

@Table
@Generated("org.jsonschema2pojo")
public class Proverb {
    private transient Long id;

    @SerializedName("id")
    @Expose
    private int proverb_id;
    @SerializedName("text")
    @Expose
    private String text;

    public Proverb(){}

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return proverb_id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.proverb_id = id;
    }

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

}
