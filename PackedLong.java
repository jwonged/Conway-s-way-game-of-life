package uk.ac.cam.dsjw2.oopjava.tick3;

public class PackedLong {
	   /*
	    * Unpack and return the nth bit from the packed number at index position;
	    * position counts from zero (representing the least significant bit)
	    * up to 63 (representing the most significant bit).
	    */
	   public static boolean get(long packed, int position) {
	   // set "check" to equal 1 if the "position" bit in "packed" is set to 1
	   packed = (packed>>position);
	   long check = (packed&1);
			   
	   return (check == 1);
	   }
	   /*
	    * Set the nth bit in the packed number to the value given
	    * and return the new packed number
	    */
	   public static long set(long packed, int position, boolean value) {
		   long tester = 1;
		   if (value) {
	         // update the value "packed" with the bit at "position" set to 1
			   tester = (tester << position);
			   packed = (packed|tester);
	      }
	      else {
	    	  if (get(packed, position)) {
	    		  tester = (tester << position);
	    		  packed = (packed^tester);
	    	  }
	         // update the value "packed" with the bit a "position" set to 0 
	      }
	      return packed;
	   }
	}