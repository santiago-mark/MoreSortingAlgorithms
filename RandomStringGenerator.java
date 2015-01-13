import java.util.Random;

/**
 Creates a random string
 @author Mark Santiago
 */
public class RandomStringGenerator 
{
	private static float prob; //controls the length of the string
	private static int num_strings; //the numbers of strings generated
	
	public static void main (String args[])
	{
		prob = Float.parseFloat(args[0]);
		num_strings = Integer.parseInt(args[1]);
		randomStrings(prob, num_strings);
	}
	
	/**
	  Generates random words, length and number of which are according to the inputs
	  @param prob the max length the word should be
	  @param num_strings the number of words to make
	  @return the all of the words
	 */
	public static String[] randomStrings(float prob, int num_strings)
	{
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int numLetters = alphabet.length();
		String[] result = new String[num_strings];
		Random random = new Random();
		for (int i = 0; i < num_strings; i++)
		{	
			String word = new String();
			char firstLetter = alphabet.charAt(random.nextInt(numLetters));
			word = Character.toString(firstLetter);
			boolean done = false;
			while (done == false)
			{
				float current = random.nextFloat();
				if (current < prob)
				{
					word = word + alphabet.charAt(random.nextInt(numLetters));
				}
				else
				{
					done = true;
				}
			}
			result[i] = word;
			System.out.println(word);
		}
		return result;	
	}
}
