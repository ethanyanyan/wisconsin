import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Performs all application specific computation for the CHSearchApp. This
 * primarily involved loading post data, efficiently searching through that
 * data, and reporting statistics about the current dataset.
 * 
 * @author BackendDeveloper, courtesy of the CS400 course staff.
 */
public class CHSearchBackendBD implements CHSearchBackendInterface {
        private HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
        private PostReaderInterface postReader;
        private int postCount;

        /**
         * Initialize backend to make use of the provided HashtableWithDuplicateKeys and
         * PostReader, and initialize the number of posts in this dataset to 0.
         * 
         * @param hashtable  placeholder by me, working implementation by
         *                   AlgorithmEngineer
         * @param postReader placeholder by me, working implementation by DataWrangler
         */
        public CHSearchBackendBD(HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable,
                        PostReaderInterface postReader) {
                this.hashtable = hashtable;
                this.postReader = postReader;
                this.postCount = 0;
        }

        /**
         * Use DataWrangler's code to load posts from the specified file, and then add
         * those posts one a time into the hashtable/dataset.
         * 
         * @param filename the path and name of the file to load post data from
         */
        public void loadData(String filename) throws FileNotFoundException {
                List<PostInterface> posts = postReader.readPostsFromFile(filename);
                for (PostInterface post : posts)
                        addPostToHashtable(post);
        }

        /**
         * Helper method extracts individual words from a post's title and body, and
         * stores mapping from each to this post.
         * 
         * @param post contains the data to add to the searchable dataset
         */
        private void addPostToHashtable(PostInterface post) {
            List<String> titleWords = getWords(post.getTitle());
            List<String> bodyWords = getWords(post.getBody());
            for (String word : titleWords)
                    hashtable.putOne("T:" + word, post);
            for (String word : bodyWords)
                    hashtable.putOne("B:" + word, post);
            postCount++;
        }

        /**
         * Helper method to extract words from a string of text, and return a single
         * list. This method also strips out punctuation and converts any upper case
         * letter to lowercase to help find variations in punctuation and casing later.
         * This is used for both storing and later searching for all words.
         * 
         * @param text contains words separated by spaces
         * @return list of lowercase words without punctuation
         */
        private List<String> getWords(String text) {
                // strip out all punctuation and make all letters to lower case
                text = text.replaceAll("[^a-zA-Z ]", "").toLowerCase();

                // break remaining letters into words around spaces
                String[] words = text.split(" ");
                return Arrays.asList(words);
        }

        /**
         * Finds all posts that contain any of the specified words in their title.
         * 
         * @param words contains space separated words
         * @return list of the strings for posts that contains any of these words, each
         *         string contains the title followed by the url for that post
         */
        public List<String> findPostsByTitleWords(String words) {
                List<String> wordList = getWords(words);
                List<String> postStrings = new ArrayList<>();
                findPostsHelper(wordList, postStrings, "T:");
                return postStrings;
        }

        /**
         * Finds all posts that contain any of the specified words in their body.
         * 
         * @param words contains space separated words
         * @return list of the strings for posts that contains any of these words, each
         *         string contains the title followed by the url for that post
         */
        public List<String> findPostsByBodyWords(String words) {
            List<String> wordList = getWords(words);
            List<String> postStrings = new ArrayList<>();
            findPostsHelper(wordList, postStrings, "B:");
            return postStrings;
        }

        /**
         * Finds all posts that contain any of the specified words in either their title
         * or their body.
         * 
         * @param words contains space separated words
         * @return list of the strings for posts that contains any of these words, each
         *         string contains the title followed by the url for that post
         */

        public List<String> findPostsByTitleOrBodyWords(String words) {
                List<String> wordList = getWords(words);
                List<String> postStrings = new ArrayList<>();
                findPostsHelper(wordList, postStrings, "T:");
                findPostsHelper(wordList, postStrings, "B:");
                return postStrings;
        }

        /**
         * Helper method that searches for posts according to a specific title or body
         * word, adds all such posts to a list, and then removes any duplicates from
         * that list.
         * 
         * @param words       is the colliection of words to search for in post dataset
         * @param postStrings is the collection (eventually without duplicates) of
         *                    matches that have been found (used as both input and
         *                    output)
         * @param typeString  is prepended to words to indicate searching in either
         *                    title "T:" or body "B:"
         */
        private void findPostsHelper(List<String> words, List<String> postStrings, String typeString) {
            for (String word : words) {
                    try {
                            // search for each word in the list, using the specified type: title/body
                            List<PostInterface> posts = hashtable.get(typeString + word);
                            // then add each of those posts as title - url strings into list
                            for (PostInterface post : posts) {
                                    String postString = post.getTitle() + " - " + post.getUrl();
                                    postStrings.add(postString);
                            }
                    } catch (NoSuchElementException e) {
                    } // when one key is not found, try the next.
            }
            // then sort string to make it easier to find and replace duplicates
            postStrings.sort(null);
            // remove duplicates strings from the postString list before returning
            for (int i = 1; i < postStrings.size(); i++)
                    if (postStrings.get(i).equals(postStrings.get(i - 1)))
                            postStrings.remove(i--);
        }

        /**
         * Displays statistics regarding the number of posts, unique words, and total
         * word-post pairs that are a part of the current dataset being searched
         * through.
         */
        public String getStatisticsString() {
                return "Dataset contains:\n" +
                                "    " + postCount + " posts\n" +
                                "    " + hashtable.getSize() + " unique words\n" +
                                "    " + hashtable.getNumberOfValues() + " total word-post pairs\n" +
                                "    note that words in titles vs bodies are counted separately";
        }

}