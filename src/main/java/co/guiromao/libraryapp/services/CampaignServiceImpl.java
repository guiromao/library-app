package co.guiromao.libraryapp.services;

import co.guiromao.libraryapp.converters.CampaignConverter;
import co.guiromao.libraryapp.dto.CampaignDto;
import co.guiromao.libraryapp.dto.MemberDto;
import co.guiromao.libraryapp.exceptions.InvalidCampaignException;
import co.guiromao.libraryapp.models.Campaign;
import co.guiromao.libraryapp.models.Member;
import co.guiromao.libraryapp.repositories.CampaignsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignsRepository campaignRepository;
    private MembersService membersService;

    public CampaignServiceImpl(CampaignsRepository repo, MembersService membService) {
        campaignRepository = repo;
        membersService = membService;
    }

    @Override
    public List<CampaignDto> findAll() {
        List<CampaignDto> result = CampaignConverter.listCampaignsToListDtos(campaignRepository.findAll());

        return result;
    }

    @Override
    public CampaignDto findById(Long campaignId) {
        if (campaignId < 1) {
            throw new InvalidCampaignException("Invalid campaign ID provided.");
        }

        return campaignRepository.findById(campaignId).isPresent() ?
                CampaignConverter.campaignToDto(campaignRepository.findById(campaignId).get())
                : null;
    }

    @Override
    public CampaignDto saveCampaign(CampaignDto dto) {
        Campaign campaign = CampaignConverter.dtoToCampaign(dto);

        if (dto.getId() == null && dto.getStartDate() == null) {
            campaign.setStartDate(new Date());
        }

        campaign = campaignRepository.saveAndFlush(campaign);

        return CampaignConverter.campaignToDto(campaign);
    }

    @Override
    public void addMemberToCampaign(Long memberId, Long campaignId) {
        if (memberId < 1 || campaignId < 1) {
            throw new IllegalArgumentException("Invalid information provided.");
        }

        Member member = membersService.findById(memberId).orElse(null);

        if (member != null) {
            Campaign campaign = campaignRepository.findById(campaignId).orElse(null);

            if (campaign != null) {
                campaign.addMember(member);
                campaignRepository.saveAndFlush(campaign);
            }
        }
    }

}
