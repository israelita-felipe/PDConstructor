package br.edu.ufrpe.uag.projetao.testes;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

public class Release extends JUnitStory {

	@Override
	public Configuration configuration(){
		/**
		return new MostUsefulConfiguration()
		.useStoryLoader(new LoadFromClasspath(this.getClass()))
		.useStoryReporterBuilder(new StoryReporterBuilder().
				withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT, Format.HTML));
		*/
		return new MostUsefulConfiguration()
		        // Onde procurar pelas estórias
		        .useStoryLoader(new LoadFromClasspath(this.getClass()))
		        // Para onde fazer os reports
		        .useStoryReporterBuilder(new StoryReporterBuilder()
		                                 .withDefaultFormats()
		                                 .withFormats(Format.CONSOLE, Format.TXT)); 
	}
 	
	@Override
	public InjectableStepsFactory stepsFactory(){
		return new InstanceStepsFactory(configuration(), new ReleaseSteps());
	}

}
