package co.guiromao.libraryapp.converters;

import co.guiromao.libraryapp.dto.CampaignDto;
import co.guiromao.libraryapp.models.Campaign;
import co.guiromao.libraryapp.utils.ModelMapperClass;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignConverter {

    private static ModelMapper modelMapper = ModelMapperClass.getInstance();

    public static Campaign dtoToCampaign(CampaignDto dto) {
        return modelMapper.map(dto, Campaign.class);
    }

    public static CampaignDto campaignToDto(Campaign campaign) {
        return modelMapper.map(campaign, CampaignDto.class);
    }

    public static List<Campaign> listDtosToListCampaigns(List<CampaignDto> listDto) {
        return listDto.stream()
                .map(dto -> modelMapper.map(dto, Campaign.class))
                .collect(Collectors.toList());
    }

    public static List<CampaignDto> listCampaignsToListDtos(List<Campaign> listCampaigns) {
        return listCampaigns.stream()
                .map(campaign -> modelMapper.map(campaign, CampaignDto.class))
                .collect(Collectors.toList());
    }

}
