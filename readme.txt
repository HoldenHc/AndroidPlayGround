Knowledge
@ activity lifecycle

@ activity lifecycle:
	
create() [enter-memory]
	start() [enter-visible (views created)]
		resume() [enter-foreground]
		pause() [exit-foregorund]
	stop() [exit-visible (views destroyed)]
destroy() [exit-memory]

when an activity is stopped:
onSaveInstanceState() is called
onCreate() receives that bundle
