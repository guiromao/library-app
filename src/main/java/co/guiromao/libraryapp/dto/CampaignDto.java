package co.guiromao.libraryapp.dto;

import co.guiromao.libraryapp.models.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {

    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Set<Member> members;
    
}
