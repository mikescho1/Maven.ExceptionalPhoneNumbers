package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jdk.nashorn.internal.objects.NativeMath.random;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber [] numbers1 = new PhoneNumber[phoneNumberCount];
        for(int i = 0; i < phoneNumberCount; i++)   {
            numbers1[i] = createRandomPhoneNumber();
        }
        return numbers1;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() {
        Random random = new Random();

        int areaCode = 100 + random.nextInt(900);
        int centralOfficeCod = 100 + random.nextInt(900);
        int phoneLineCode = 1000 + random.nextInt(9000);

        return createPhoneNumberSafely(areaCode, centralOfficeCod, phoneLineCode);
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        String phoneNumConcat = String.format("(%d)-%d-%d", areaCode, centralOfficeCode, phoneLineCode);
        try {
            return createPhoneNumber(phoneNumConcat);
        }
        catch (InvalidPhoneNumberFormatException e) {
            logger.info(String.format("%s is not a valid phone number", phoneNumConcat));
            return null;
        }
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException{
        logger.info("Attempting to create a new PhoneNumber object with a value of " + phoneNumberString);

        return new PhoneNumber(phoneNumberString);
    }
}
