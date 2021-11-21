package co.guiromao.libraryapp.controllers;

import co.guiromao.libraryapp.dto.CampaignDto;
import co.guiromao.libraryapp.dto.MemberDto;
import co.guiromao.libraryapp.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library/campaigns")
public class CampaignsController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<CampaignDto>> getAllCampaigns() {
        return new ResponseEntity<>(campaignService.findAll(), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignDto campaignDto) {
        return new ResponseEntity<>(campaignService.saveCampaign(campaignDto), HttpStatus.CREATED);
    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<CampaignDto> getCampaign(@PathVariable Long campaignId) {
        CampaignDto campaign = campaignService.findById(campaignId);
        ResponseEntity<CampaignDto> result = campaign != null ?
                new ResponseEntity<>(campaign, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return result;
    }

    @PutMapping("/{campaignId}")
    public ResponseEntity addMemberToCampaign(@PathVariable Long campaignId, @RequestBody MemberDto dto) {
        campaignService.addMemberToCampaign(dto, campaignId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
