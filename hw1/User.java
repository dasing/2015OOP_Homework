public class User{
    
    int id;
    int state;
    char [] card = new char [15];
    int [] ori_card = new int [14];
    
    public User( int player_id ){
        
        //assign player id
        id = player_id;
        state = 1; // 1 means alive, 0 means dead
        
        //initialize card array, N means no cards, Y means have card(index 14, 15 store joker cards)
        for( int i=0; i<15; i++ )
            card[i] = 'N';
            
    }
    
    public User( User user ){
        id = user.id;
        state = user.state;
        for( int i=0; i<15; i++ )
            card[i]=user.card[i];
    }
    
}
