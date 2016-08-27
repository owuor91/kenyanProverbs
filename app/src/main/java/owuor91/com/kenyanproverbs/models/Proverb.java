
package owuor91.com.kenyanproverbs.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

@Generated("org.jsonschema2pojo")
public class Proverb extends SugarRecord{

    @SerializedName("text")
    @Expose
    private String text;

    public Proverb(){}
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
