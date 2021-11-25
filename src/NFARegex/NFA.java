package NFARegex;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.ArrayList;

public class NFA {



    private static NFA instance=null;
    private String cur_state;
    private String initial_state;
    private final String []Q={
            "q0","q1","q2","q3"
    };
    private final char []Sigma={
            'n','i','c','a','l'
    };
    private final char [][]Delta={
            {' ','n',' ','l'},
            {' ',' ','i',' '},
            {' ',' ',' ','c'},
            {'a',' ',' ',' '}

    };
    private final String  []F={
            "q3"
    };

    private NFA()
    {
        initial_state="q0";
        cur_state="q0";
    }


    public static NFA getInstance(){
        if(instance==null){
            instance=new NFA();
        }
        return instance;
    }
    public String nextState(char current){

        //search in the delta array for next state
        boolean founded=false;
        int next_index=0;
        //if final state return #
        char char_index=this.cur_state.toString().charAt(1);
        int index= ((int) char_index)-48;
            for(int i=0;i<4;i++) {
                if (Delta[index][i] == current) {
                    founded = true;
                    next_index = i;
                }
                if (founded)
                    return ("q" + next_index);
            }


        return null;
    }

    public String  getCur_state() {
        return cur_state;
    }

    public void setCur_state(String cur_state) {
        this.cur_state = cur_state;
    }
}
