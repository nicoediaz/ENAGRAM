<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

  $usr_id=$_POST['id'];
  $usr_action=$_POST['popup_action'];
  $usr_post_lenght=$_POST['post_lenght'];

  require_once 'connect.php';

  $sql_action_id = "SELECT * FROM popup_actions WHERE description='$usr_action'";

  $response1 = mysqli_query($conn, $sql_action_id);

  if(mysqli_num_rows($response1)===1){#action description found

    $row= mysqli_fetch_assoc($response1);

    $action_id= $row['action_id'];

    $sql_insert_action = "INSERT INTO user_activity (popup_action, user_id, post_lenght) VALUES ('$action_id','$usr_id',$usr_post_lenght)";

    if(mysqli_query($conn, $sql_insert_action)){#insert successful
      $result["success"]="1";
      $result["message"]="success";

      echo json_encode($result);
      mysqli_close($conn);
    }
    else{#insert failed
      $result["success"]="0";
      $result["message"]="insert failed";

      echo json_encode($result);
      mysqli_close($conn);
    }
  }
  else{#error in querying 'popup_actions'
    $result["success"]="0";
    $result["message"]="query failed";

    echo json_encode($result);
    mysqli_close($conn);
  }
}
?>
