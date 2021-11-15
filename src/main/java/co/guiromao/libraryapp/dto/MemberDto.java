package co.guiromao.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String name;
    private boolean isActive;
    private Set<BookDto> booksGot;
    private Map<Date, Boolean> activePeriods;

}
