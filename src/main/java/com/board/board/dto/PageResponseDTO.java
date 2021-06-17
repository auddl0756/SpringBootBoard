package com.board.board.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@ToString
@Data
public class PageResponseDTO<DTO,ENTITY> {
    private List<DTO> dtoList;

    public PageResponseDTO(Page<ENTITY> response, Function<ENTITY,DTO> function){
        dtoList = response.stream()
                            .map(function)
                            .collect(Collectors.toList());

//        totalPage = (int)response.getTotalElements();
        totalPage = response.getTotalPages();
        makePageList(response.getPageable());
    }

    private int nowPage;
    private int size;
    private int totalPage;
    private int startPage,endPage;
    private boolean prevExist,nextExist;
    private List<Integer> pageList;

    private void makePageList(Pageable pageable){
        this.nowPage=pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(nowPage/10.0))*size;
        startPage = tempEnd-9;
        prevExist = (startPage>1);
        nextExist = (totalPage > tempEnd);
        endPage = Math.min(totalPage,tempEnd);

        pageList = new ArrayList<>();
        for(int i=startPage;i<=endPage;i++) pageList.add(i);
    }
}
