update media m set img_url='lecture/lecture_default.jpg' where type='audio';
update media m set img_url='lecture/bg_default.jpg' where type='audio' and
exists(select 1 from media_scripture ms where ms.media_id=m.id and scripture_id=1);
update media m set img_url='lecture/sb_default.jpg' where type='audio' and
exists(select 1 from media_scripture ms where ms.media_id=m.id and scripture_id=2);
update media m set img_url='lecture/cc_default.jpg' where type='audio' and
exists(select 1 from media_scripture ms where ms.media_id=m.id and scripture_id=3);
update media m set img_url='lecture/np_default.jpg' where type='audio' and
exists(select 1 from media_scripture ms where ms.media_id=m.id and scripture_id=4);
update media m set img_url='lecture/nn_default.jpg' where type='audio' and
exists(select 1 from media_scripture ms where ms.media_id=m.id and scripture_id=5);
update media m set img_url='lecture/si_default.jpg' where type='audio' and
exists(select 1 from media_scripture ms where ms.media_id=m.id and scripture_id=6);
update media m set img_url='lecture/kadambini_default.jpg' where type='audio' and
exists(select 1 from media_scripture ms where ms.media_id=m.id and scripture_id=7);
update media m set img_url='lecture/ramayana_default.jpg' where type='audio' and
exists(select 1 from media_scripture ms where ms.media_id=m.id and scripture_id=8);

update media m set img_url='lecture/parikram_default.jpg' where type='audio' and category_id=1;
update media m set img_url='lecture/celebration_default.jpg' where type='audio' and category_id=2;
update media m set img_url='lecture/treatment_default.jpg' where type='audio' and category_id=3;
update media m set img_url='lecture/public_default.jpg' where type='audio' and category_id=4;
update media m set img_url='lecture/darshan_default.jpg' where type='audio' and category_id=5;
update media m set img_url='lecture/kirtan_default.jpg' where type='audio' and category_id=6;
update media m set img_url='lecture/initiation_default.jpg' where type='audio' and category_id=7;
update media m set img_url='lecture/discussion_default.jpg' where type='audio' and category_id=8;