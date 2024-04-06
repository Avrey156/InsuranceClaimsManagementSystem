package entity;

import service.ClaimService;
import service.CustomerService;

import java.util.List;

public class ClaimProcessManagerImpl implements ClaimProcessManager{
    @Override
    public void add(Claim claim) {

    }

    @Override
    public void update(Claim claim) {

    }

    @Override
    public void delete(String claimId) {

    }

    @Override
    public Claim getOne(String claimId) {
        return ClaimService.getOne(claimId);
    }

    @Override
    public List<Claim> getAll() {
        return ClaimService.getAll();
    }
}
