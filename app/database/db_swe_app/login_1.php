<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

  $name=$_POST['name'];
  $password=$_POST['password'];

  require_once 'connect.php';

  $sql_exists_usr= "SELECT * FROM users_table WHERE name='$name'";

  $response = mysqli_query($conn, $sql_exists_usr);

  if(mysqli_num_rows($response)===1){#User found

    $row = mysqli_fetch_assoc($response);

    if(password_verify($password, $row['password'])){#Password correct
      $result["success"]="1";
      $result["message"]="success";

      echo json_encode($result);
      mysqli_close($conn);
    }
    else{#Password incorrect
      $result["success"]="2";
      $result["message"]="wrong password";

      echo json_encode($result);
      mysqli_close($conn);
    }

  }
  else{ #User not found
      $result["success"]="0";
      $result["message"]="no user";

      echo json_encode($result);
      mysqli_close($conn);
  }

}
?>
