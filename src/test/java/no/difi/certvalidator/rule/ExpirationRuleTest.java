package no.difi.certvalidator.rule;

import no.difi.certvalidator.api.CertificateValidationException;
import no.difi.certvalidator.api.FailedValidationException;
import no.difi.certvalidator.testutil.X509TestGenerator;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.operator.OperatorCreationException;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;



public class ExpirationRuleTest extends X509TestGenerator {

    @Test
    public void shouldValidateAValidCertificate() throws CertificateValidationException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, CertIOException, OperatorCreationException {
        ExpirationRule validator = new ExpirationRule();

        X509Certificate cert = createX509Certificate(DateTime.now().minusDays(10).toDate(), DateTime.now().plusDays(10).toDate());

        validator.validate(cert);
    }

    @Test(expectedExceptions = FailedValidationException.class)
    public void shouldInvalidateAExpiredCertificate() throws CertificateValidationException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, CertIOException, OperatorCreationException {
        ExpirationRule validator = new ExpirationRule();

        X509Certificate cert = createX509Certificate(DateTime.now().minusDays(10).toDate(), DateTime.now().minusDays(2).toDate());

        validator.validate(cert);
    }

    @Test(expectedExceptions = FailedValidationException.class)
    public void shouldInvalidateANotNotbeforeCertificate() throws CertificateValidationException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, CertIOException, OperatorCreationException {
        ExpirationRule validator = new ExpirationRule();

        X509Certificate cert = createX509Certificate(DateTime.now().plusDays(10).toDate(), DateTime.now().plusDays(20).toDate());

        validator.validate(cert);
    }
}
