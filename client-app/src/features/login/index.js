import React, { useState } from "react";
import SignIn from "./signIn";
import SignUp from "./signUp";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";
import Link from "@material-ui/core/Link";
import Paper from "@material-ui/core/Paper";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles(theme => ({
  root: {
    height: "100vh"
  },
  image: {
    backgroundImage: "url(https://source.unsplash.com/random)",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover",
    backgroundPosition: "center"
  },
  paper: {
    margin: theme.spacing(8, 4),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  form: {
    width: "100%",
    marginTop: theme.spacing(1)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  }
}));

export default function Login({ onTokenUpdate }) {
  const classes = useStyles();

  const [isSignInVisible, setIsSignInVisible] = useState(true);

  return (
    <Grid container component="main" className={classes.root}>
      <Grid item xs={false} sm={4} md={7} className={classes.image} />
      <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
        {isSignInVisible ? (
          <SignIn onTokenUpdate={onTokenUpdate} />
        ) : (
          <SignUp
            onTokenUpdate={onTokenUpdate}
            showSignIn={setIsSignInVisible}
          />
        )}
        <Grid container>
          <Grid item xs>
            <Link href="#" variant="body2">
              Forgot password?
            </Link>
          </Grid>
          <Grid item>
            <Button onClick={() => setIsSignInVisible(!isSignInVisible)}>
              {isSignInVisible
                ? "Don't have an account? Sign Up"
                : "Already a user? Sign In"}
            </Button>
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
}
