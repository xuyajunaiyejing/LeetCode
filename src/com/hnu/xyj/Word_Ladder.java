package com.hnu.xyj;

import java.util.*;

/**
 * Created by xyj on 15/11/17.
 *  Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the word list

 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5. 
 */
public class Word_Ladder
{
	public int ladderLength(String start, String end, Set<String> dict)
	{
		if (dict == null || dict.size() == 0)
		{
			return 0;
		}

		HashSet<String> hash = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		hash.add(start);

		int length = 1;
		while (!queue.isEmpty())
		{
			length++;
			int size = queue.size();
			for (int i = 0; i < size; i++)
			{
				String word = queue.poll();
				for (String nextWord : getNextWords(word, dict))
				{
					if (hash.contains(nextWord))
					{
						continue;
					}
					if (nextWord.equals(end))
					{
						return length;
					}

					hash.add(nextWord);
					queue.offer(nextWord);
				}
			}
		}
		return 0;
	}

	// replace character of a string at given index to a given character
	// return a new string
	private String replace(String s, int index, char c)
	{
		char[] chars = s.toCharArray();
		chars[index] = c;
		return new String(chars);
	}

	// get connections with given word.
	// for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
	// it will return ['hit', 'hog']
	private ArrayList<String> getNextWords(String word, Set<String> dict)
	{
		ArrayList<String> nextWords = new ArrayList<String>();
		for (char c = 'a'; c <= 'z'; c++)
		{
			for (int i = 0; i < word.length(); i++)
			{
				if (c == word.charAt(i))
				{
					continue;
				}
				String nextWord = replace(word, i, c);
				if (dict.contains(nextWord))
				{
					nextWords.add(nextWord);
				}
			}
		}
		return nextWords;
	}

}
