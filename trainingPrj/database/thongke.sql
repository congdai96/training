select authority_name,male,female,max19,min20,notfull,notage
from mst_role
left join (
	select authority_id,count(*) as male
	from mst_user where gender_id=1 group by authority_id) as tbMale
on mst_role.authority_id = tbMale.authority_id
left join(
	select authority_id,count(*) as female
	from mst_user where gender_id=2 group by authority_id) as tbFemale
on mst_role.authority_id=tbFemale.authority_id
left join(
	select authority_id,count(*) as max19
	from mst_user where age<=19 group by authority_id) as tbMax19
on mst_role.authority_id=tbMax19.authority_id
left join(
	select authority_id,count(*) as min20
	from mst_user where age>19 group by authority_id) as tbMin20
on mst_role.authority_id=tbMin20.authority_id
left join(
	select authority_id,count(*) as notfull
	from mst_user where authority_id is null or gender_id is null or age is null 
	group by authority_id) as tbNotFull
on mst_role.authority_id=tbNotFull.authority_id
left join(
	select authority_id,count(*) as notage
	from mst_user where age is null group by authority_id) as tbNotAge
on mst_role.authority_id=tbNotAge.authority_id



