package entity;

/**
 * @author <Nguyen Ngoc Minh Thu - S3941327>
 */

import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();
}
