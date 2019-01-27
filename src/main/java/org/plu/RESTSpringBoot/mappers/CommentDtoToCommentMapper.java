package org.plu.RESTSpringBoot.mappers;

import org.plu.RESTSpringBoot.model.dto.CommentDto;
import org.plu.RESTSpringBoot.model.entities.Comment;

public class CommentDtoToCommentMapper implements Mapper<CommentDto, Comment> {
    @Override
    public Comment map(CommentDto from) {
        if(from == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setComment(from.getComment());
        comment.setUsername(from.getUsername());
        return comment;
    }
}
