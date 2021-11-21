package co.guiromao.libraryapp.services;

import co.guiromao.libraryapp.dto.CampaignDto;
import co.guiromao.libraryapp.dto.MemberDto;
import co.guiromao.libraryapp.models.Campaign;

import java.util.List;

public interface CampaignService {

    List<CampaignDto> findAll();

    CampaignDto findById(Long campaignId);

    CampaignDto saveCampaign(CampaignDto dto);

    void addMemberToCampaign(Long memberId, Long campaignId);

}
