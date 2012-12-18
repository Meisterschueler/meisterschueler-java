package de.meisterschueler.alignment;

import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationDiscrete;
import be.ac.ulg.montefiore.run.jahmm.OpdfDiscrete;
import be.ac.ulg.montefiore.run.jahmm.OpdfDiscreteFactory;
import be.ac.ulg.montefiore.run.jahmm.apps.sample.SimpleExample.Packet;

public class HiddenMarkov {
	public HiddenMarkov() {
		OpdfDiscreteFactory<Packet> factory =
				new OpdfDiscreteFactory<Packet>(Packet.class);
		Hmm<ObservationDiscrete<Packet>> hmm = 
				new Hmm<ObservationDiscrete<Packet>>(2, factory);

		hmm.setPi(0, 0.95);
		hmm.setPi(1, 0.05);

		hmm.setOpdf(0, new OpdfDiscrete<Packet>(Packet.class, new double[] { 0.95, 0.05 }));
		hmm.setOpdf(1, new OpdfDiscrete<Packet>(Packet.class, new double[] { 0.2, 0.8 }));

		hmm.setAij(0, 1, 0.05);
		hmm.setAij(0, 0, 0.95);
		hmm.setAij(1, 0, 0.1);
		hmm.setAij(1, 1, 0.9);
	}
}
