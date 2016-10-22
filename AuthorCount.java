package uk.ac.cam.dsjw2.oopjava.tick3;

public class AuthorCount implements Comparable<AuthorCount> {

	   private String author;
	   private int count;
		
	   public AuthorCount(String author) {
	      this.author = author;
	      this.count = 1;
	   }
		
	   public void inc() {
	      count++;
	   }

	   @Override
	   public int hashCode() {
	      //TODO: Uniquely identify this object based on author name alone.
	   }

	   @Override
	   public boolean equals(Object obj) {
	      //TODO: return true iff obj is an AuthorCount object 
	      //      and this.author equals obj.author.
	      //Hint: You might like to investigate what the 'instanceof' operator does.
	   }

	   @Override
	   public int compareTo(AuthorCount o) {
	      //TODO: return -1 if 'this' rank orders before 'o', 
	      //      return 0 if 'this' and 'o' are equal,
	      //      return 1 if 'this' rank orders after 'o'
	      //      to do so order first by 'count' (largest first), and if counts
	      //      are equal, then order by 'name' as a tie-breaker.
	   }

	   @Override
	   public String toString() {
	      //TODO: return a string whose:
	      //      first 20 characters contains the author name (left-aligned) 
	      //      and final 3 characters contains the count (right-aligned)
	      //Hint: You might find String.format(...) helpful here.
	   }
	}