CREATE TABLE IF NOT EXISTS publishers(
    publisher_id CHAR(6),
    name char(30),
    url char(80)
);
insert into publishers values ('0201','Addison-Wesley','www.aw-bc.com');
insert into publishers values ('0407','John Wiley & Sons','www.wiley.com');

select * from publishers;
-- delete from publishers;