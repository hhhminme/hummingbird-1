package com.hummingbird.backend.food.domain;

import com.hummingbird.backend.category.domain.Category;
import com.hummingbird.backend.food.dto.UpdateFoodDto;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@ToString
@Getter
@Entity
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "content",length = 100)
    private String content;

    @Column(name = "price")
    private int price;

    @Column(name = "fileId")
    private Long fileId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Food(Long id, String name, String content, int price, Long fileId, Category category) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.price = price;
        this.fileId = fileId;
        this.category = category;
    }






    public void UpdateFood(UpdateFoodDto dto){
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.content = dto.getContent();
        this.fileId = dto.getFileId();
    }
}
