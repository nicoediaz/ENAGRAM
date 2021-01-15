<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

  $usr_id=$_POST['id'];

  require_once 'connect.php';

  $sql_action_id = "SELECT count(*) FROM user_activity WHERE DATE(timestamp) = CURDATE() AND user_id = '$usr_id'";
  $response1 = mysqli_query($conn, $sql_action_id);

  $sql_action_id = "SELECT count(*) FROM user_activity WHERE user_id = '$usr_id' AND popup_action = 1";
  $response2 = mysqli_query($conn, $sql_action_id);

  $sql_action_id = "SELECT count(*) FROM user_activity WHERE user_id = '$usr_id' AND popup_action = 0";
  $response3 = mysqli_query($conn, $sql_action_id);

  $sql_action_id = "SELECT TIMESTAMPDIFF(MINUTE,timestamp,CURRENT_TIMESTAMP) AS timediff FROM user_activity WHERE timestamp = (SELECT MAX(timestamp) FROM user_activity) AND user_id = '$usr_id'";
  $response4 = mysqli_query($conn, $sql_action_id);

  $result =array();
  $result['data']=array();

  #if(mysqli_num_rows($response1)===1 and mysqli_num_rows($response2)===1 and mysqli_num_rows($response3)===1 and mysqli_num_rows($response4)===1){#action description found
  if($response1 and $response2 and $response3 and $response4){#action description found

    if(mysqli_num_rows($response1)===1 and mysqli_num_rows($response2)===1 and mysqli_num_rows($response3)===1 and mysqli_num_rows($response4)===1){
      $row1= mysqli_fetch_assoc($response1);
      $today_popups= $row1['count(*)'];

      $row2= mysqli_fetch_assoc($response2);
      $ignored_popups= $row2['count(*)'];

      $row3= mysqli_fetch_assoc($response3);
      $accepted_popups= $row3['count(*)'];

      $row4= mysqli_fetch_assoc($response4);
      $last_popup= $row4['timediff'];

      $result["success"]="1";
      $result["message"]="success";

      $index['today_popups']=$today_popups;
      $index['ignored_popups']=$ignored_popups;
      $index['accepted_popups']=$accepted_popups;
      $index['last_popup']=$last_popup;
    }
    else{
      $result["success"]="1";
      $result["message"]="success";

      $index['today_popups']=0;
      $index['ignored_popups']=0;
      $index['accepted_popups']=0;
      $index['last_popup']=0;
    }

    array_push($result['data'],$index);

    echo json_encode($result);
    mysqli_close($conn);
  }
  else{#error in querying 'popup_actions'
    $result["success"]="0";
    $result["message"]="query failed";

    echo json_encode($result);
    mysqli_close($conn);
  }
}
?>
