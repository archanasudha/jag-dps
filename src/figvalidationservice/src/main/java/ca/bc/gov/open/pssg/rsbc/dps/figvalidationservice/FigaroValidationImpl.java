package ca.bc.gov.open.pssg.rsbc.dps.figvalidationservice;

import ca.bc.gov.open.ords.figcr.client.api.FigvalidationsApi;
import ca.bc.gov.open.ords.figcr.client.api.handler.ApiException;
import ca.bc.gov.open.ords.figcr.client.api.model.ValidateApplicantForSharingOrdsResponse;
import ca.bc.gov.open.ords.figcr.client.api.model.ValidateApplicantPartyIdOrdsResponse;
import ca.bc.gov.open.pssg.rsbc.dps.figvalidationservice.exception.FigaroValidationServiceException;
import ca.bc.gov.open.pssg.rsbc.dps.figvalidationservice.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FigaroValidationImpl class
 * <p>
 * Note: Calls ORDS clients
 *
 * @author shaunmillargov
 */
@Service
public class FigaroValidationImpl implements FigaroValidation {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FigvalidationsApi ordsapi;

    @Override
    public LocateMatchingApplicantsResponse locateMatchingApplicants(LocateMatchingApplicantsRequest lmr)
            throws FigaroValidationServiceException {

        // TODO - replace the following dummy response with a call to ORDS

        // Note: Call response code and response message comes from the ORDS call.

        LocateMatchingApplicantsResponse resp = new LocateMatchingApplicantsResponse(
                "Success - found Party Id 209",
                1,
                "209",
                "MOUSE",
                "MINNIE",
                null,
                "1950-01-10",
                "999999",
                "DISNEY",
                "F");

        return resp;

    }

    @Override
    public ValidateApplicantServiceResponse validateApplicantService(ValidateApplicantServiceRequest lmr)
            throws FigaroValidationServiceException {

        // TODO - replace the following dummy response with a call to ORDS

        // Note: Call response code and response message comes from the ORDS call.

        ValidateApplicantServiceResponse resp = new ValidateApplicantServiceResponse(
                "success",
                0,
                "N"
        );

        return resp;

    }

    @Override
    /*
     * service method to get the response for /validateApplicantForSharing requests
     */
    public ValidateApplicantForSharingOrdsResponse validateApplicantForSharing(
            ValidateApplicantForSharingRequest validateApplicantForSharingRequest)
            throws FigaroValidationServiceException {


        try {
            return ordsapi.validateApplicantForSharing(validateApplicantForSharingRequest.getApplPartyId(), validateApplicantForSharingRequest.getJurisdictionType());
        } catch (ApiException ex) {
            logger.error("Exception caught as Figaro Validator Service, ValidatePartyId : " + ex.getMessage());
            ex.printStackTrace();
            throw new FigaroValidationServiceException(ex.getMessage());
        }

    }

    @Override
    public ValidateApplicantPartyIdOrdsResponse validateApplicantPartyId(String applPartyId)
            throws FigaroValidationServiceException {

        try {
            return ordsapi.validateApplicantPartyId(applPartyId, null);
        } catch (ApiException ex) {
            logger.error("Exception caught as Figaro Validator Service, ValidatePartyId : " + ex.getMessage());
            ex.printStackTrace();
            throw new FigaroValidationServiceException(ex.getMessage());
        }

    }

}

