package com.crio.starter.service;

import lombok.RequiredArgsConstructor;
import com.crio.starter.data.DataSequences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SequenceGeneratorService {
    
    private final MongoTemplate mongoTemplate;

    public long generateSequence(String seqName) {

        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq_no", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        DataSequences counter = mongoTemplate.findAndModify(query, update, options, DataSequences.class);
        return (counter != null) ? counter.getSeq_no() : 1;

    }

}
