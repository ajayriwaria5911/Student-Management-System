function StudentCard({name, course, email}) {

  return (

			    <div
					      style =
					      {{
					        border:"1px solid black",
					        padding:"10px",
					        marginBottom:"10px"
					      }}
					    >
		
				      <h3>{name}</h3>
				      <p>{course}</p>
				      <p>{email}</p>
				      
				 </div>

  );
}

export default StudentCard;