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
        if (useLowercase) groups++;
        if (useDigits) groups++;
        if (useLowercase) groups++;
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
                } else if (ch >= 0 && ch <= 9) {
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
            message += "\n - Τουλάχιστον ένα κεφαλαίο γράμμα";
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


    
}