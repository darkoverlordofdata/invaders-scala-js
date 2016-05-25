### Using entitas-cli with kotlin and libgdx: Part I - Setup

I like using an entity component system, but ecs also requires some boilerplate. 
So,inspired by Entitas-CSharp, I've written a command line utility, entitas-cli, 
to help me get a project started.

I'm going to re-create a classic Space Invaders game in Scala.js. 
I've created a basic project named InvaderZ, usng this url.

You will need sbt, an editor (I used IntelliJ Community Edition), and the entitas-cli tool. 
Entitas-cli is written in coffee-script, so you'll need to have nodejs installed to run this:

sudo npm install -g entitas-cli

Entitas-cli saves information in a json file. The generate command uses the stored 
information to build your project scaffold from templates by using using liquid templates. 
So, the project root, I paste the following commands into a terminal window:

```
    entitas init invaders 
    entitas create -c Player 
    entitas create -c Bullet 
    entitas create -c Alien
    entitas create -c Position x:Float y:Float
    entitas create -c View sprite:com.scalawarrior.scalajs.createjs.Sprite
    entitas create -s Render IInitializeSystem IExecuteSystem
    entitas create -s Input IExecuteSystem
    entitas create -s Collision IExecuteSystem
    entitas create -s Physics IExecuteSystem
    entitas create -s Destroy IExecuteSystem
    entitas create -s CreateAliens IInitializeSystem IExecuteSystem
    entitas generate -p scala
```

Now I hve a ./entitas.json in my project root.

```
    {
      "namespace": "invaders",
      "src": "lib/src",
      "output": {
        "javascript": "web/src/invaders/generatedExtensions.js",
        "typescript": "lib/src/generatedComponents.ts",
        "declaration": "lib/ext/invaders.d.ts"
      },
    ...}
```

The defaults are for a typescript layout, so I make the following changes:
```
      "namespace": "com.darkoverlordofdata.invaderz",
      "src": "src/main/scala",
      "output": {
        "generated": "GeneratedComponents.scala"
      },
```
and generate the output:

entitas generate -p scala mutable=var

But before we can compile, well need to add the entitas.jar. The project is pre-release, but there is a jar file on github - (url). So create a 'libs' folder in your project root, place the jar file in the folder. Then in AndroidStudio, right click on the jar and select 'Add as library'. 

At this point, we should be able to compile and run the base project, though all we will see at this point is the BadLogic logo from libGDX. 

Wait - I need velocity, too, for my physics to work. No worry. I can just do the following:

    entitas create -c Velocity x:Float y:Float
    entitas create -c Bounds width:Float height:Float
    entitas create -c Destroy
    entitas generate -p scala mutable=var

