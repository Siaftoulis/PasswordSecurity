import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import javax.print.DocFlavor.STRING;

/** 
*  
Author:        Γράψτε εδώ το όνομα σας 
*  Written:       ../../2026 
*  Last updated:  ../../2026 
* 
*  
*  
*   
Compilation command:    
Execution command:      
*  Γράψτε εδώ πληροφορίες σχετικά με το τι κάνει  
*  το πρόγραμμα 
* 
*/
public class PasswordSecurity {
    // Built-in word list of common, easy-to-remember words
    private static final String[] WORD_LIST = {
    "apple","brave","cloud","dance","eagle","flame","grace","house",
    "ivory","joker","kneel","lemon","mango","noble","ocean","piano",
    "queen","river","stone","tiger","ultra","vivid","waltz","xenon",
    "yacht","zebra","amber","blaze","crisp","drift","ember","frost",
    "gleam","haven","inlet","jewel","karma","lunar","maple","north",
    "orbit","pearl","quill","ridge","solar","torch","unity","vapor",
    "wheat","yield","acorn","birch","cedar","daisy","finch","glade",
    "holly","iris","junco","kelp","lilac","moose","newt","otter",
    "poppy","quail","robin","swift","tulip","vixen","wren","algae",
    "brook","clove","dunes","fjord","grove","heath","isle","jade",
    "knoll","marsh","opal","pine","quartz","reef","sage","thorn",
    "vale","willow","bison","crane","dove","egret","falcon","gecko",
    "heron","ibis","jaguar","kite","lynx","mink","raven","stoat",
    "tapir","vole","wombat","yak","antler","burrow","canopy","delta",
    "flint","gorge","harbor","jungle","lagoon","meadow","nimbus",
    "oasis","plateau","quasar","ravine","summit","tundra","valley",
    "abyss","basin","caldera","dune","fen","geyser","hammock","jetty",
    "levee","moraine","outcrop","quicksand","rift","scree","talus",
    "volcano","beacon","compass","dynamo","engine","forge","hammer",
    "iron","lantern","magnet","needle","piston","relay","signal",
    "timber","valve","winch","alloy","bolt","cable","diesel","filter",
    "gasket","hinge","impact","joint","kernel","lathe","module",
    "nozzle","pulley","rivet","spring","torque","weld","anchor",
    "brace","clasp","dowel","elbow","flange","gauge","hook","joist",
    "latch","mount","notch","pivot","ratchet","shim","zephyr","ash"
    };
    /*---------------------------------------------------------
    *
    *---------------------------------------------------------*/


    /*---------------------------------------------------------
    * Part 1 : method for password generation
    *---------------------------------------------------------*/
    /* checking that at least one lowercase, upercase, number and special character is used
        */
    public static String generatePassword (int length, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars) {


        // 1.Validation
        if (!useLowercase && !useUppercase && !useDigits && !useSpecialChars) {
            return "Error: At least one of each characher type is needed!";
        }

        // 2.Count the nunbers of selected characters groups
        int groups = 0;
        if (useUppercase) groups++;
        if (useLowercase) groups++;
        if (useDigits) groups++;
        if (useSpecialChars) groups++;
        
        if (length < groups) {
            return "Error: Length of password is too short to include all selected character types!";
        }
        // 3.Chararacter pools
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String special = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~*?";
        
        String charactersPool = "";
        char[] passwordArray = new char[length];
        int index = 0;

        // 4.Insure that there is at least one character from each group
        if (useUppercase) {
            passwordArray[index++] = upper.charAt((int) (Math.random() * upper.length()));
            charactersPool+= upper;
        }
        if (useLowercase) {
            passwordArray[index++] = lower.charAt((int) (Math.random() * lower.length()));
            charactersPool+= lower;
        }
        if (useDigits) {
            passwordArray[index++] = numbers.charAt((int) (Math.random() * numbers.length()));
            charactersPool+= numbers;
        }
        if (useSpecialChars) {
            passwordArray[index++] = special.charAt((int) (Math.random() * special.length()));
            charactersPool+= special;
        }
        //5. Fill the remaing mumbers randomly from the combined pool
        while (index < length){
            int randIndex = (int) (Math.random() * charactersPool.length());
            passwordArray[index++] =charactersPool.charAt(randIndex);
        }
        
        // 6.Shuffle numbers randomly using  Fisher-Yates to avoid predictable patterns
        for (int i = passwordArray.length - 1; i>0; i--){
            int randIndex = (int) (Math.random()* (i + 1));
            char temp = passwordArray[i];
            passwordArray[randIndex] = temp;
        }
        return new String(passwordArray);
    }
    
    

    //Default overload with all characters types enabled
    public String generatePassword(int length) {

        return generatePassword(length, true, true, true, true);
    }

    //Shake_case allias for compatability
    public static String genete_password(int length , boolean useLowercase , boolean useUppercase , boolean useDigits , boolean useSpecialChars){
        return generatePassword(length, useLowercase, useUppercase, useDigits, useSpecialChars);
    }

    /*---------------------------------------------------------
    * Part 2
    *---------------------------------------------------------*/
    //enforce string password requirments
    /**
     * Validates whether a password satisfies the security policy requirements:
     * - Minimum length of 8 characters
     * - At least one lowercase letter
     * - At least one uppercase letter
     * - At least one digit
     * - At least one special character
     */

        public static String enforceStrongPasswordRequirements(String password) {
        
        String special = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~*?";
        
        boolean hasMinLenght = (password != null && password.length() >= 8 );
        boolean hasLower  = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        //  Inspect chars in the password
        if (password !=  null ){
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);

                //check that password has all requirments
                if (ch >= 'a' && ch <= 'z') {
                    hasLower =true;
                } else if (ch >= 'A' && ch <= 'Z') {
                    hasUpper = true;
                } else if (ch >= '0' && ch <= '9') {
                    hasDigit = true;
                } else if (special.indexOf(ch) >= 0) {
                    hasSpecial = true;
                }
            }
        }
        // Checks all the criteria if are satifiead
        if (hasDigit && hasLower && hasMinLenght && hasSpecial && hasUpper) {
            return "Ο κωδικός πρόσβασής σας είναι ισχυρός και πληροί όλες τις απαιτήσεις!";
        }

        //build feeback string listing all missing requirement
        String message = "Ο κωδικός πρόσβασής σας λείπει:";
        if(!hasMinLenght) {
            message += "\n - Τουλάχιστον 8 χαρακτήρες";
        }
        if (!hasDigit) {
            message +="\n - Τουλάχιστον ένα ψηφίο";
        }
        if (!hasLower) {
            message += "\n - Τουλάχιστον ένα μικρό γράμμα";
        }
        if (!hasUpper) {
            message +="\n - Τουλάχιστον ένα κεφαλαίο γράμμα";
        }
        if (!hasSpecial) {
            message +="\n - Τουλάχιστον ένας ειδικός χαρακτήρας";
        }
        return message;
    }
    //Shake_case alias for compatibility
    public static String enforce_strong_password_requirements(String password){
        return enforceStrongPasswordRequirements(password);
    }






    /*---------------------------------------------------------
    * Part 3
    *---------------------------------------------------------*/
    //blablabla yap
    //This is the calc method =+=
    public static Long calculate_possibilities(String password){
        if(password== null || password.length() == 0){
            return 0L;
        }
        String special = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~*?";
        boolean hasLower  = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        //Determine which cahracter pools are present in the password
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                hasLower = true;
            } else if (ch >= 'A' && ch <= 'Z') {
                hasUpper = true;
            } else if (ch >= '0' && ch <= '9') {
                hasDigit = true;
            } else if (special.indexOf(ch) >= 0) {
                hasSpecial = true;
            }
        }

        // Calculate the combineds characters set size
        int charactersSetSize = 0;
        
        if (hasLower) charactersSetSize += 26;
        if (hasUpper) charactersSetSize += 26;
        if (hasDigit) charactersSetSize += 10;
        if (hasSpecial) charactersSetSize += 32;
        if (charactersSetSize == 00){
            return 0L;
        }
        // Formula : total_possibilities = Math.pow(character_set_size , password_length)
        return (long)Math.round(Math.pow(charactersSetSize, password.length()));
    }

    public static long calculatePossibilities(String password) {
        return calculate_possibilities(password);
    }

    /*---------------------------------------------------------
    * Part 4 : Estimate Time to Crack
    *---------------------------------------------------------*/
    // Calculates how long it takes to brute-force crack the password
    // assuming a speed of 1,000,000 attempts per second
    // Returns array: [timeInSeconds, timeInYears]
    public static double[] estimatePasswordCrackingTime(String password) {
        long totalPossibilities = calculatePossibilities(password);
        double guessesPerSecond = 1000000.0; // 1 million attempts per second

        // 1. Calculate time in seconds
        double timeInSeconds = (double) totalPossibilities / guessesPerSecond;

        // 2. Convert seconds to years (365.25 days to count leap years)
        double timeInYears = timeInSeconds / (60.0 * 60.0 * 24.0 * 365.25);

        return new double[] { timeInSeconds, timeInYears };
    }

    //Shake_case alias for compatibility
    public static double[] estimate_password_cracking_time(String password) {
        return estimatePasswordCrackingTime(password);
    }

    /*---------------------------------------------------------
    * Part 5 : Dictionary Attack Simulation
    *---------------------------------------------------------*/
    // Default overload using "Rockyou.txt" dictionary file
    public static Object[] dictionaryAttack(String targetPassword) {
        return dictionaryAttack(targetPassword, "Rockyou.txt");
    }

    // Simulates a dictionary attack by checking target against words in a file
    // Returns array: [elapsedTimeInSeconds (or null if not found), total attempts]
    public static Object[] dictionaryAttack(String targetPassword, String dictionaryFile) {
        long startTime = System.nanoTime();
        int attempts = 0;

        File file = new File(dictionaryFile);
        // Check if the wordlist file exists before trying to read
        if (!file.exists()) {
            return new Object[] { null, 0 };
        }

        // Read through dictionary file line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                attempts++;
                // Trim whitespace and check for a match
                if (line.trim().equals(targetPassword)) {
                    long endTime = System.nanoTime();
                    double elapsedSeconds = (endTime - startTime) / 1_000_000_000.0;
                    return new Object[] { elapsedSeconds, attempts };
                }
            }
            // Scanned entire wordlist without finding a match
            return new Object[] { null, attempts };
        } catch (IOException e) {
            // Handle file reading error
            return new Object[] { null, attempts };
        }
    }

    //Shake_case alias for compatibility
    public static Object[] dictionary_attack(String targetPassword) {
        return dictionaryAttack(targetPassword);
    }

    public static Object[] dictionary_attack(String targetPassword, String dictionaryFile) {
        return dictionaryAttack(targetPassword, dictionaryFile);
    }

    /*---------------------------------------------------------
    * Part 6 : Generate Memorable Passphrase
    *---------------------------------------------------------*/
    // Default overload: 4 words, hyphen-separated, capitalized, with trailing number
    public static String generatePassphrase(int wordCount) {
        return generatePassphrase(wordCount, "-", true, true);
    }

    // Generates a memorable passphrase picking random words from WORD_LIST
    public static String generatePassphrase(int wordCount, String separator,
                                            boolean capitalizeEach, boolean appendNumber) {
        // 1. Validation - check that wordCount is at least 3 words
        if (wordCount < 3) {
            return "Error: Passphrase must contain at least 3 words.";
        }

        String result = "";
        // 2. Pick random words from WORD_LIST and join them together
        for (int i = 0; i < wordCount; i++) {
            // Select random word from the list
            int randomIndex = (int) (Math.random() * WORD_LIST.length);
            String word = WORD_LIST[randomIndex];

            // Capitalize first letter if requested, else make it all lowercase
            if (capitalizeEach && word.length() > 0) {
                word = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            } else {
                word = word.toLowerCase();
            }

            // Append separator before word (skip for the very first word)
            if (i > 0) {
                result += separator;
            }
            result += word;
        }

        // 3. Append a random 2-digit number (10 to 99) at the end if requested
        if (appendNumber) {
            int randomTwoDigit = 10 + (int) (Math.random() * 90);
            result += separator + randomTwoDigit;
        }

        return result;
    }

    //Shake_case alias for compatibility
    public static String generate_passphrase(int wordCount) {
        return generatePassphrase(wordCount);
    }

    public static String generate_passphrase(int wordCount, String separator, boolean capitalizeEach, boolean appendNumber) {
        return generatePassphrase(wordCount, separator, capitalizeEach, appendNumber);
    }

    /*---------------------------------------------------------
    * Part 7 : Main Test Method
    *---------------------------------------------------------*/
    public static void main(String[] args) {
        // Testing Part 1: password generation
        System.out.println("=== Part 1: Generate Password ===");
        String pwd = generatePassword(12, true, true, true, true);
        System.out.println("Generated (12 chars, all types): " + pwd);
        System.out.println(generatePassword(3, true, true, true, true));
        System.out.println(generatePassword(8, false, false, false, false));

        // Testing Part 2: enforce strong password requirements
        System.out.println("\n=== Part 2: Enforce Strong Password Requirements ===");
        System.out.println(enforceStrongPasswordRequirements("P@ssw0rd"));
        System.out.println(enforceStrongPasswordRequirements("password"));

        // Testing Part 3: calculate password possibilities
        System.out.println("\n=== Part 3: Calculate Password Possibilities ===");
        long possibilities = calculatePossibilities("P@ssw0rd");
        System.out.println("Total possible combinations: " + possibilities);

        // Testing Part 4: brute-force crack time estimation
        System.out.println("\n=== Part 4: Estimate Time to Crack ===");
        double[] times = estimatePasswordCrackingTime("P@ssw0rd");
        System.out.printf("Estimated time to crack: %.6f seconds (~%.8f years)%n", times[0], times[1]);

        // Testing Part 5: dictionary attack simulation
        System.out.println("\n=== Part 5: Dictionary Attack ===");
        Object[] result = dictionaryAttack("password123");
        if (result[0] != null) {
            System.out.printf("Password found in %.2f seconds after %d attempts.%n", result[0], result[1]);
        } else {
            System.out.printf("Password not found after %d attempts.%n", result[1]);
        }

        // Testing Part 6: memorable passphrase generation
        System.out.println("\n=== Part 6: Generate Memorable Passphrase ===");
        // 4 words, hyphen-separated, capitalized, with number (default style)
        System.out.println(generatePassphrase(4));
        // 3 words, space-separated, lowercase, no number
        System.out.println(generatePassphrase(3, " ", false, false));
        // 5 words, underscore-separated, capitalized, with number
        System.out.println(generatePassphrase(5, "_", true, true));
        // Error case: too few words
        System.out.println(generatePassphrase(2));
    }


    
}