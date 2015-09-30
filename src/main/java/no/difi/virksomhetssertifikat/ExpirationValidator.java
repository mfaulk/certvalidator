package no.difi.virksomhetssertifikat;

import no.difi.virksomhetssertifikat.api.CertificateValidator;
import no.difi.virksomhetssertifikat.api.FailedValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * Validate validity of certificate.
 */
public class ExpirationValidator implements CertificateValidator {

    private static Logger logger = LoggerFactory.getLogger(ExpirationValidator.class);

    public boolean isValid(X509Certificate cert) throws FailedValidationException {
        try{
            cert.checkValidity(new Date());
        }catch (CertificateNotYetValidException e){
            logger.debug("Certificate not yet valid. ({})", cert.getSerialNumber());
            throw new FailedValidationException("Certificate does not have a valid expiration date.");
        }catch (CertificateExpiredException e){
            logger.debug("Certificate expired. ({})", cert.getSerialNumber());
            throw new FailedValidationException("Certificate does not have a valid expiration date.");
        }

        return true;
    }

    public String faultMessage(X509Certificate cert) {
        return "Certificate does not have a valid expiration date";
    }
}
