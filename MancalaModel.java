// CS 151 - Project Group SSR
// MancalaModel.java


import java.util.ArrayList;
import javax.swing.event.*;


/**
  The ManacalaModel represents the [M]odel of the MVC pattern.
*/
public class MancalaModel
{

	ArrayList<PitComponent> data = new ArrayList<PitComponent>();
	MancalaPit manA;
	MancalaPit manB;
	ArrayList listeners;
	int numberStones;
	int boardStyle;
	
	
	public void addHole(PitComponent sH)
	{
		data.add(sH);
	}
	
	

	   
   /**
      Constructs a MancalaModel object
      @param d the data to model
   */
   public MancalaModel()
   {
      listeners = new ArrayList();
   }

   /**
      Returns the current state of the MancalaModel data.
      @return the data in an ArrayList
   */
   public ArrayList getData()
   {
      return (ArrayList) data.clone();
   }

   
   /**
   Attach a listener to the Model
   @param c the listener
	*/
	public void attach(ChangeListener c)
	{
	   listeners.add(c);
	}

	
   /**
      Change the data in the model at a particular location
      @param location the index of the field to change
      @param value the new value
   */
   public void update(int location, int value)
   {
	   
	   if(location < 6)
	   {
		   for(int i = location-1; i >= location-value && i >=0; i--) //
		   {
			   
			   data.get(i).hole.addStone();
		   
			   value--;
		   }
		   if(value > 0)
		   {
			   manB.mancala.addStone();
			   value--;
		   }
		   if(value > 0)
		   {
			   
			   for(int i = 6; i < 6+value; i++)
			   {
				   
				   data.get(i).hole.addStone();
			   }
			   
		   }
	   
	   }
	   else
	   {
		   int counter = value;
		   for(int i = location+1; i <= location+value && i < 12; i++)
		   {
			   
			   data.get(i).hole.addStone();
			   counter--;
		   }
		   if(counter > 0)
		   {
			   
			   manA.mancala.addStone();
			   counter--;

		   }
		   if(counter > 0)
		   {
			   for(int i = 5; i > 5-counter && i >=0; i--) //
			   {
				   
				   data.get(i).hole.addStone();
			   }
		   }
		   
	   }


	   
	   	   
//	   for(int i = 0; i < listeners.size(); i++)
//	   {
		   ChangeListener l = (ChangeListener) listeners.get(0);
		   l.stateChanged(new ChangeEvent(this));
         
//	   } 
   }
   
   
   /**
  	XXXX
	*/
	public void undo()
	{
		// do something
	}
   
   
	/**
		XXXX
	*/
	public void endgame()
	{
		// do something
	}
	
	/**
	XXXX
	*/
	public void initializeStones()
	{
		for(int i = 0; i < 12; i++)
		{
			data.get(i).hole.setNbStones(numberStones);
		
//	        ChangeListener l = (ChangeListener) listeners.get(i);

//	        l.stateChanged(new ChangeEvent(this));
		}
		
        ChangeListener l = (ChangeListener) listeners.get(0);

        l.stateChanged(new ChangeEvent(this));
		


		// do something
	}
   
   
}











