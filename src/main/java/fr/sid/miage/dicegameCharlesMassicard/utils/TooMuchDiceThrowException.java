package fr.sid.miage.dicegameCharlesMassicard.utils;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class TooMuchDiceThrowException extends Exception {

	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * The serial Version unique identifiant : usefull for serialization.
	 */
	private static final long serialVersionUID = 123456789L;
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * The code error of the exception.
	 */
	private String code;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

    /**
     * Construtor for the exception TooMuchDiceThrowException.
     * Explain the exception with an error messsage.
     * 
     * @param message The error message to explain the exception.
     */
    public TooMuchDiceThrowException(String message) {
        super(message);
    }
	
    /**
     * Construtor for the exception TooMuchDiceThrowException.
     * Explain the exception with an error messsage.
     * 
     * @param code The error code of the exception.
     * @param message The error message to explain the exception.
     */
    public TooMuchDiceThrowException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    /**
     * Construtor for the exception TooMuchDiceThrowException.
     * Explain the exception with an error messsage.
     * 
     * @param code The error code of the exception.
     * @param message The error message to explain the exception.
     * @param cause  The message to explain the cause of the exception.
     */
    public TooMuchDiceThrowException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
    
	/* ========================================= Main ================================================== */ /*=========================================*/
}
