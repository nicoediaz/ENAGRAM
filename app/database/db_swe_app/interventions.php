<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

  require_once 'connect.php';

  $sql_interventions= "SELECT interventions.id, intervention_categories.category, interventions.message, interventions.risk
  FROM interventions, intervention_categories WHERE interventions.category = intervention_categories.id";

  $response = mysqli_query($conn, $sql_interventions);

  $result =array();
  $result['data']=array();

  if(mysqli_num_rows($response)>0){

    $result['success']="1";
    $result['message']="success";

    while($r = mysqli_fetch_assoc($response)) {
      array_push($result['data'],$r);
    }

    echo json_encode($result);
    mysqli_close($conn);
  }
  else{

    $result['success']="0";
    $result['message']="error";

    echo json_encode($result);
    mysqli_close($conn);
  }
}
?>
